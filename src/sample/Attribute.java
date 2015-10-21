package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Piotr Lasek on 15-04-15.
 */
public class Attribute {

    private Integer id;
    private String name;
    private String label;

    public Attribute(Integer id, String name, String label) {
       this.name = name;
    }

    /**
     *
     * @param rs
     */
    public Attribute(ResultSet rs) {
        try {
            setId(rs.getInt("id"));
            setName(rs.getString("name").trim().toLowerCase());
            setLabel(rs.getString("label").trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
