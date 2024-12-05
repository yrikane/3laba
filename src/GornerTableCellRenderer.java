import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.Introspector;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter =
            (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        if (col == 2 && value instanceof Boolean) {
            // Обработка третьего столбца с типом Boolean
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected((Boolean) value);
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return checkBox;
        }

        // Обработка числовых значений для первых двух столбцов
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);



        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        }
        else if(((int)(Double.parseDouble(formattedDouble)) % 2 == 0) && (((Double.parseDouble(formattedDouble)) > 1) || (Double.parseDouble(formattedDouble) < -1)))
        {
            panel.setBackground(Color.GRAY);
        }
        else {
            panel.setBackground(Color.WHITE);
        }
        return panel;
    }



    

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
