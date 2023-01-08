package carshop.model.enums;

public enum Statuses {
    CREATED, AVAILIBLE, PAID, SOLD;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}