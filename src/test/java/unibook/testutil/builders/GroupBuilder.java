package unibook.testutil.builders;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.model.person.Student;

public class GroupBuilder {
    public static final String DEFAULT_GROUP_NAME = "W16-1";

    private String name;
    private ObservableList<Student> members;
    private ObservableList<LocalDateTime> meetingTimes;
    private Module module;


    public GroupBuilder() {
        this.name = DEFAULT_GROUP_NAME;
        this.members = FXCollections.observableArrayList();
        this.meetingTimes = FXCollections.observableArrayList();
        //set to default module from module builder
        this.module = new ModuleBuilder().build();
    }

    public GroupBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupBuilder withMembers(ObservableList<Student> members) {
        this.members = members;
        return this;
    }

    public GroupBuilder withMeetingTimes(ObservableList<LocalDateTime> meetingTimes) {
        this.meetingTimes = meetingTimes;
        return this;
    }

    public GroupBuilder withModule(Module module) {
        this.module = module;
        return this;
    }

    public Group build() {
        return new Group(name, module, members, meetingTimes);
    }
}
