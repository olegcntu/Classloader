package obj;


public class Mhetods {
    String name;
    String type;
    String mod;
    String oblsee;


    public void setName(String name) {


        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMods(String st) {
        String[] result = st.split("\\s+");
        if (result[0].equals("protected")) {
            oblsee = result[0];
        } else if (result[0].equals("private")) {
            oblsee = result[0];
        } else if (result[0].equals("public")) {
            oblsee = result[0];
        } else {
            oblsee = "default";
        }

        if (result[1].equals("static")) {
            mod = result[1];
        } else if (result[0].equals("final")) {
            mod = result[1];
        } else if (result[0].equals("transient")) {
            mod = result[1];
        } else {
            mod = null;
        }
    }

    @Override
    public String toString() {
        return " Mhetods " + " \n Name=" + name + "; \n Type=" + type + "; \n Mod=" + mod + "; \n Oblsee=" + oblsee + ";";
    }

}
