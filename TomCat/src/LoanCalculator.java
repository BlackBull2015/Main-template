import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



@WebServlet("/loan-calculator")
public class LoanCalculator extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputString = request.getParameter("loanInputs");
        String names = request.getParameter("");
        // Example input: { "amount": 100000, "rate": 5.5, "months": 360 };
        // Example String: String inputString = "{ \"amount\": 100000, \"rate\": 5.5, \"months\": 360 }";
        double loanAmount = 200000;
        double annualInterestRateInPercent = 5.5;
        long loanPeriodInMonths = 360;
        try {

            System.out.println("Before creation of JSON");
            JSONObject inputValues = new JSONObject(inputString);
            System.out.println("I have created JSON object");
            loanAmount = inputValues.getDouble("amount");
            annualInterestRateInPercent = inputValues.getDouble("rate");
            loanPeriodInMonths = inputValues.getLong("months");
        } catch (Exception e) {
            System.out.println("Servlet died");
            e.printStackTrace();
        }
        PaymentInfo info = new PaymentInfo(loanAmount, annualInterestRateInPercent, loanPeriodInMonths);
        PrintWriter out = response.getWriter();
        //System.out.println();
        out.println(new JSONObject(info));
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
