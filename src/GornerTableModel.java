import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    Double fff = 6.556600000;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
        System.out.println(twopair(fff.toString()));
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    @Override
    public int getColumnCount() {
        // В данной модели три столбца
        return 3;
    }

    @Override
    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка
        // исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ * НОМЕР_СТРОКИ
        double x = from + step * row;

        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        } else if (col == 1) {
            // Если запрашивается значение 2-го столбца, то это значение многочлена
            Double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        } else {
            // Если запрашивается значение 3-го столбца, то это проверка "значение больше нуля?"
            Double result = (Double) getValueAt(row, 1);
            //System.out.println(result);
            return twopair(result.toString());
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "есть ли совпадающие цифры рядом";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 2) {
            // Для третьего столбца возвращаем класс Boolean
            return Boolean.class;
        }
        return Double.class;
    }
    public static boolean twopair (String number){
        String numberStr = number.replace(".", "");

        if (numberStr.length() < 4) {
            return false;
        }

        for(int i = 0; i < 1; i++)
        {

            if(numberStr.charAt(i) == numberStr.charAt(i+1))
            {

                if(numberStr.charAt(i+2) == numberStr.charAt(i+3))

                    return true;
            }
        }
        return false;
    }
}
