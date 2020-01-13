package task.domain;

public class DataTableRequest {
    private int draw;
    private int start;
    private int length;
    private int sortColumn;
    private String sortDirection;
    private String filter;

    public DataTableRequest() {
        this.draw = 0;
        this.start = 0;
        this.length = 0;
        this.sortColumn = 0;
        this.sortDirection = "";
        this.filter = "";
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(int sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "DataTableRequest{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", sortColumn=" + sortColumn +
                ", sortDirection='" + sortDirection + '\'' +
                ", filter='" + filter + '\'' +
                '}';
    }
}
