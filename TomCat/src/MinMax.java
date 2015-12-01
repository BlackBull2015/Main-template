import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/MinMax")
public class MinMax extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sts = "";
        String inputString = request.getParameter("num");
        try {

            System.out.println("Before creation of JSON");
            JSONObject inputValues = new JSONObject(inputString);
            System.out.println("I have created JSON object");


            double[] array = new double[3];

             array[0]= inputValues.getDouble("one");
             array[1] = inputValues.getDouble("two");
             array[2]= inputValues.getDouble("three");
            //***********************************************************//

            System.out.println(array[0]);
            System.out.println(array[1]);
            System.out.println(array[2]);
            //***********************************************************//

            Arrays.sort(array);

            double max = array[0];
            double min = array[array.length-1];


            JSONObject jsn = new JSONObject();
            jsn.put("max",max);
            jsn.put("min",min);

            PrintWriter out = response.getWriter();
            out.println(jsn);


        } catch (Exception e) {
            System.out.println("Servlet died");
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println(sts);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
