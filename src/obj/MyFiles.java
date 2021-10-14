package obj;

public class MyFiles {
    private String name;
    private String size;
    private String dateUpdate;
    private String info;

    @Override
    public String toString() {
        return " MyFiles: " +
                ";\n Name=" + name  +
                ";\n Size=" + size +
                ";\n DateUpdate=" + dateUpdate +
                ";\n Info=" + info ;
    }

    public MyFiles(String name, String size, String dateUpdate, String info) {
        String stName[]=name.split("/");
        this.name =  stName[stName.length-1];
        this.size = size;
        this.dateUpdate = dateUpdate;
        this.info=info;
    }
}
