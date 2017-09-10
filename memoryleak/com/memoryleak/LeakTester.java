package com.memoryleak;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class LeakTester extends JPanel {

    List<Object> leakyList = new ArrayList<Object>();
    
    public LeakTester() {
        buildGui();
    }
    
    void buildGui() {
        setLayout(new GridBagLayout());
        GridBagConstraints labelGbc = new GridBagConstraints();
        labelGbc.gridx = labelGbc.gridy = 0;
        labelGbc.weightx = labelGbc.weighty = 0;
        labelGbc.anchor = GridBagConstraints.EAST;
        labelGbc.fill = GridBagConstraints.NONE;
        labelGbc.insets = new Insets(2,2,2,1);
        
        GridBagConstraints fieldGbc = new GridBagConstraints();
        fieldGbc.gridx = 1;
        fieldGbc.gridy = 0;
        fieldGbc.weightx = 1;
        fieldGbc.weighty = 0;
        fieldGbc.anchor = GridBagConstraints.WEST;
        fieldGbc.fill = GridBagConstraints.HORIZONTAL;
        fieldGbc.insets = new Insets(2,1,2,2);
        
        add(new JLabel("Leak Type"), labelGbc);
        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButton;
        
        final JRadioButton classRadioButton = radioButton = new JRadioButton("LeakyClass", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton objectRadioButton = radioButton = new JRadioButton("LeakyObject", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton deserializedRadioButton = radioButton = new JRadioButton("LeakyDeserializedObject", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton newInstanceRadioButton = radioButton = new JRadioButton("LeakyClassDotNewInstanceObject", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton primitiveArrayRadioButton = radioButton = new JRadioButton("LeakyPrimitiveArray (int[])", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton objectArrayRadioButton = radioButton = new JRadioButton("LeakyObjectArray", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton multiDimArrayRadioButton = radioButton = new JRadioButton("LeakyMultiDimensionalArray", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        final JRadioButton cloneRadioButton = radioButton = new JRadioButton("LeakyCloneObject", true);
        group.add(radioButton);
        add(radioButton, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;

        add(new JLabel("Number of New Leaked Objects"), labelGbc);
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 1, Integer.MAX_VALUE, 10));
        add(spinner, fieldGbc);
        
        labelGbc.gridy = ++fieldGbc.gridy;
        
        JButton button = new JButton("Leak Objects");
        add(button, fieldGbc);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (classRadioButton.isSelected())
                    leakClasses((Integer) spinner.getValue());
                else if (objectRadioButton.isSelected())
                    leakObjects((Integer) spinner.getValue());
                else if (primitiveArrayRadioButton.isSelected())
                    leakPrimitiveArrays((Integer) spinner.getValue());
                else if (objectArrayRadioButton.isSelected())
                    leakObjectArrays((Integer) spinner.getValue());
                else if (multiDimArrayRadioButton.isSelected())
                    leakMultiDimArrays((Integer) spinner.getValue());
                else if (deserializedRadioButton.isSelected())
                    leakDeserialized((Integer) spinner.getValue());
                else if (newInstanceRadioButton.isSelected())
                    leakNewInstance((Integer) spinner.getValue());
                else if (cloneRadioButton.isSelected())
                    leakClone((Integer) spinner.getValue());
                else
                    throw new IllegalStateException("Huh?");
            }
        });
    }
    

    void leakClasses(int numberToLeak) {
        try {
            for (int i=0; i<numberToLeak; i++) {
                ClassLoader cl = new MyClassLoader();
                Class c = cl.loadClass(LeakyClass.class.getName());
                leakyList.add(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void leakObjects(int numberToLeak) {
        for (int i=0; i<numberToLeak; i++) {
            leakyList.add(new LeakyObject());
        }
    }
    
    void leakPrimitiveArrays(int numberToLeak) {
        for (int i=0; i<numberToLeak; i++) {
            leakyList.add(new int[0]);
        }
    }
    
    void leakObjectArrays(int numberToLeak) {
        for (int i=0; i<numberToLeak; i++) {
            leakyList.add(new LeakyObjectArray());
        }
    }
    
    void leakMultiDimArrays(int numberToLeak) {
        for (int i=0; i<numberToLeak; i++) {
            leakyList.add(new LeakyMultiDimensionalArray[0][0]);
        }
    }
    
    static final byte SERIALIZED_OBJECT[];
    static {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(new LeakyDeserializedObject());
            oos.close();
            bos.close();
            SERIALIZED_OBJECT = bos.toByteArray();
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
    
    void leakDeserialized(int numberToLeak) {
        ByteArrayInputStream bis;
        ObjectInputStream ois;
        try {
            for (int i=0; i<numberToLeak; i++) {
                bis = new ByteArrayInputStream(SERIALIZED_OBJECT);
                ois = new ObjectInputStream(bis);
                leakyList.add(ois.readObject());
                ois.close();
                bis.close();
            }
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
    
    void leakNewInstance(int numberToLeak) {
        try {
            for (int i=0; i<numberToLeak; i++) {
                leakyList.add(LeakyClassDotNewInstanceObject.class.newInstance());
            }
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
    
    class LeakyClass{}
    class LeakyObject{}
    static class LeakyDeserializedObject implements Serializable {}
    static class LeakyClassDotNewInstanceObject{
        public LeakyClassDotNewInstanceObject() {}
    }
    class LeakyObjectArray{}
    class LeakyMultiDimensionalArray{}
    
    
    class LeakyCloneObject implements Cloneable {
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    
    LeakyCloneObject myLeakyCloneable = new LeakyCloneObject();
    
    void leakClone(int numberToLeak) {
        try {
            for (int i=0; i<numberToLeak; i++) {
                leakyList.add(myLeakyCloneable.clone());
            }
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
    
    
    class MyClassLoader extends ClassLoader {
//        protected Class<?> findClass(String name)
//                throws ClassNotFoundException {
//            return loadClass(name);
//        }
        
        public Class loadClass(String name) throws ClassNotFoundException {
            String classpathName = name.replace('.', '/') + ".class";
            InputStream is = getResourceAsStream(classpathName);
            if (is == null) throw new ClassNotFoundException();
            else {
                try {
                    byte bytes[] = new byte[1024];
                    int length;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    while ( (length = is.read(bytes)) > -1) {
                        bos.write(bytes, 0, length);
                    }
//                    System.out.println("done.");
                    byte classBytes[] = bos.toByteArray();
//                    System.out.println("size: " + classBytes.length);
                    return defineClass(name, classBytes, 0, classBytes.length);
                } 
                catch (Exception e) {
//                  e.printStackTrace();
//                  throw new ClassNotFoundException();
                    return super.loadClass(name);
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new LeakTester());
        f.pack();
        f.setVisible(true);
    }
}
