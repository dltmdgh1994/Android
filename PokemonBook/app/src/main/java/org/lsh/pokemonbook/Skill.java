package org.lsh.pokemonbook;

import java.io.Serializable;

public class Skill implements Serializable {
    //intent에 객체를 담기 위해 Serializable이 필요
    String skill_name;
    String skill_dps;
    String skill_type;
    String skill_sort;
    String skill_power;
    String skill_eps;
    String skill_time;

    public Skill(String skill_name, String skill_dps, String skill_type, String skill_sort, String skill_power, String skill_eps, String skill_time) {
        this.skill_name = skill_name;
        this.skill_dps = skill_dps;
        this.skill_type = skill_type;
        this.skill_sort = skill_sort;
        this.skill_power = skill_power;
        this.skill_eps = skill_eps;
        this.skill_time = skill_time;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_dps() {
        return skill_dps;
    }

    public void setSkill_dps(String skill_dps) {
        this.skill_dps = skill_dps;
    }

    public String getSkill_type() {
        return skill_type;
    }

    public void setSkill_type(String skill_type) {
        this.skill_type = skill_type;
    }

    public String getSkill_sort() {
        return skill_sort;
    }

    public void setSkill_sort(String skill_sort) {
        this.skill_sort = skill_sort;
    }

    public String getSkill_power() {
        return skill_power;
    }

    public void setSkill_power(String skill_power) {
        this.skill_power = skill_power;
    }

    public String getSkill_eps() {
        return skill_eps;
    }

    public void setSkill_eps(String skill_eps) {
        this.skill_eps = skill_eps;
    }

    public String getSkill_time() {
        return skill_time;
    }

    public void setSkill_time(String skill_time) {
        this.skill_time = skill_time;
    }
}
