package fr.pantheonsorbonne.model;

public class InsuranceQuote {
    private Long id;
    private String insuranceProvider;
    private double quoteAmount;
    private String coverageDetails;

    // Constructors
    public InsuranceQuote() {}

    public InsuranceQuote(Long id, String insuranceProvider, double quoteAmount, String coverageDetails) {
        this.id = id;
        this.insuranceProvider = insuranceProvider;
        this.quoteAmount = quoteAmount;
        this.coverageDetails = coverageDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public double getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(double quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }
}
