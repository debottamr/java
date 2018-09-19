package decorator;

public abstract class ColumDecorator implements Report
{
    private Report decoratedReport;
     
    public ColumDecorator(Report report){
        this.decoratedReport = report;
    }
     
    public String getFirstColumnData() {
        return decoratedReport.getFirstColumnData();
    }
     
    @Override
    public Object[][] getReportData(String reportId) {
        return decoratedReport.getReportData(reportId);
    }
}
