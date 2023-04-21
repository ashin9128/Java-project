import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList extends JFrame implements ActionListener {

    private final ArrayList<String> tasks;
    private final DefaultListModel<String> listModel;
    private final JList<String> taskList;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final Color backgroundColor;
    private final Color textColor;
    private final Font font;

    public ToDoList() {
        super("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        backgroundColor = new Color(255, 240, 230); // light peach color
        textColor = new Color(80, 80, 80); // dark gray color
        font = new Font("Arial", Font.PLAIN, 16); // custom font

        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setBackground(backgroundColor);
        taskList.setForeground(textColor);
        taskList.setFont(font);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(backgroundColor);

        addButton = new JButton("Add Task");
        addButton.addActionListener(this);
        addButton.setBackground(new Color(120, 170, 200)); // blue color
        addButton.setForeground(Color.white);
        addButton.setFont(font.deriveFont(Font.BOLD));
        buttonPanel.add(addButton);

        editButton = new JButton("Edit Task");
        editButton.addActionListener(this);
        editButton.setBackground(new Color(120, 170, 200)); // blue color
        editButton.setForeground(Color.white);
        editButton.setFont(font.deriveFont(Font.BOLD));
        buttonPanel.add(editButton);

        deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(this);
        deleteButton.setBackground(new Color(120, 170, 200)); // blue color
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(font.deriveFont(Font.BOLD));
        buttonPanel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBackground(backgroundColor);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ToDoList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = JOptionPane.showInputDialog(this, "Enter task:");
            if (task != null && !task.isEmpty()) {
                tasks.add(task);
                listModel.addElement(task);
            }
        } else if (e.getSource() == editButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a task to edit.");
                return;
            }

            String currentTask = tasks.get(selectedIndex);
            String newTask = JOptionPane.showInputDialog(this, "Edit task:", currentTask);
            if (newTask != null && !newTask.isEmpty()) {
                tasks.set(selectedIndex, newTask);
                listModel.set(selectedIndex, newTask);
            }
        } else if (e.getSource() == deleteButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?");
            if (confirm == JOptionPane.YES_OPTION) {
                tasks.remove(selectedIndex);
                listModel.remove(selectedIndex);
            }
        }
    }
}
