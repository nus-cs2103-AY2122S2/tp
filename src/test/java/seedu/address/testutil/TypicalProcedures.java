package seedu.address.testutil;

import seedu.address.model.procedure.Procedure;

public class TypicalProcedures {
    public static final Procedure REPAIR_ROUTER_PROC = new ProcedureBuilder().withInfo("Repair Router")
            .withDate("01/01/2000 12:15").withCost("13.50").withCompletion("true")
            .build();
    public static final Procedure REPLACE_WIRES_PROC = new ProcedureBuilder().withInfo("Replace wires")
            .withDate("05/03/2005 18:30").withCost("23.00").withCompletion("true")
            .build();
    public static final Procedure TROUBLESHOOT_NETWORK_PROC = new ProcedureBuilder()
            .withInfo("Troubleshoot network").withDate("10/05/2015 10:15").withCost("5.05").withCompletion("true")
            .build();
}
