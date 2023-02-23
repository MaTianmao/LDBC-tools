package nebula;

import com.google.common.collect.ImmutableMap;
import org.ldbcouncil.snb.impls.workloads.nebula.converter.NebulaConverter;
import org.ldbcouncil.snb.impls.workloads.converter.Converter;
import org.ldbcouncil.snb.impls.workloads.nebula.NebulaID;
import org.ldbcouncil.snb.driver.workloads.interactive.*;
import org.junit.Test;
import java.util.Date;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Calendar;

public class ConvertLdbcParam {

        public static String QUERY_PREFIX = ConvertLdbcParam.class.getResource("/").getFile()
                        + "../../queries/interactive-complex-";
        public static String QUERY_SUFFIX = ".ngql";

        public Map<Integer, String> queries = new HashMap<>();

        public ConvertLdbcParam() {
                for (int i = 1; i <= 14; i++) {
                        String filename = QUERY_PREFIX + i + QUERY_SUFFIX;
                        // System.out.println(filename);
                        // System.out.println(ConvertLdbcParam.class.getResource("/").getFile());
                        try {
                                String query = new String(Files.readAllBytes(Paths.get(filename)));
                                queries.put(i, query);
                        } catch (IOException e) {
                                System.err.printf("Unable to load query from file: %s\n", filename);
                        }
                }
                // System.out.println(queries.get(2));
        }

        public Converter getConverter() {
                return new NebulaConverter();
        }

        protected String getParameterPrefix() {
                return "$";
        }

        protected String getParameterPostfix() {
                return "";
        }

        protected Date addDays(Date startDate, int days) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DATE, days);
                return cal.getTime();
        }

        public String paramFilling(Integer queryType, Map<String, Object> parameterSubstitutions) {
                String querySpecification = queries.get(queryType);
                for (String parameter : parameterSubstitutions.keySet()) {
                        querySpecification = querySpecification.replace(
                                        getParameterPrefix() + parameter + getParameterPostfix(),
                                        (String) parameterSubstitutions.get(parameter));
                }
                return querySpecification;
        }

        @Test
        public void convertQuery1Param() {
                LdbcQuery1 query = new LdbcQuery1(65504, "John", 20);
                System.out.println("Nebula LDBC query1 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ1())));
                System.out.println("firstName: " + getConverter().convertString(query.getFirstName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ1())))
                                .put("firstName", getConverter().convertString(query.getFirstName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(1, paramMap));
        }

        @Test
        public void convertQuery2Param() {
                LdbcQuery2 query = new LdbcQuery2(2199023521857L, new Date(1313798400000L), 20);
                System.out.println("Nebula LDBC query2 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ2())));
                System.out.println("maxDate: " + getConverter().convertDateTime(query.getMaxDate()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ2())))
                                .put("maxDate", getConverter().convertDateTime(query.getMaxDate()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(2, paramMap));
        }

        @Test
        public void convertQuery3Param() {
                LdbcQuery3 query = new LdbcQuery3(8796093163356L, "Papua_New_Guinea", "Namibia",
                                new Date(1306886400000L), 52,
                                20);
                System.out.println("Nebula LDBC query3 parameter convert.");
                System.out.println("personId: " + getConverter().convertString(
                                NebulaID.PERSON_ID_PREFIX + getConverter().convertId(query.getPersonIdQ3())));
                System.out.println("countryXName: " + getConverter().convertString(query.getCountryXName()));
                System.out.println("countryYName: " + getConverter().convertString(query.getCountryYName()));
                System.out.println("startDate: " + getConverter().convertDateTime(query.getStartDate()));
                System.out.println("durationDays: " + getConverter().convertInteger(query.getDurationDays()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ3())))
                                .put("countryXName", getConverter().convertString(query.getCountryXName()))
                                .put("countryYName", getConverter().convertString(query.getCountryYName()))
                                .put("startDate", getConverter().convertDateTime(query.getStartDate()))
                                .put("durationDays", getConverter().convertInteger(query.getDurationDays()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(3, paramMap));
        }

        @Test
        public void convertQuery4Param() {
                LdbcQuery4 query = new LdbcQuery4(2199023472955L, new Date(1317427200000L), 52,
                                20);

                Date endDate = addDays(query.getStartDate(), query.getDurationDays());
                System.out.println("Nebula LDBC query4 parameter convert.");
                System.out.println("personId" +
                                getConverter().convertString(
                                                NebulaID.PERSON_ID_PREFIX
                                                                + getConverter().convertId(query.getPersonIdQ4())));
                System.out.println("startDate" + getConverter().convertDateTime(query.getStartDate()));
                System.out.println("endDate" + getConverter().convertDateTime(endDate));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ4())))
                                .put("startDate", getConverter().convertDateTime(query.getStartDate()))
                                .put("endDate", getConverter().convertDateTime(endDate))
                                .build();

                System.out.println();
                System.out.println(paramFilling(4, paramMap));
        }

        @Test
        public void convertQuery5Param() {
                LdbcQuery5 query = new LdbcQuery5(8796093163356L, new Date(1348272000000L), 20);

                System.out.println("Nebula LDBC query5 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ5())));
                System.out.println("minDate: " + getConverter().convertDateTime(query.getMinDate()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ5())))
                                .put("minDate", getConverter().convertDateTime(query.getMinDate()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(5, paramMap));
        }

        @Test
        public void convertQuery6Param() {
                LdbcQuery6 query = new LdbcQuery6(65504L, "Gordon_Brown", 20);

                System.out.println("Nebula LDBC query6 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ6())));
                System.out.println("tagName: " + getConverter().convertString(query.getTagName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ6())))
                                .put("tagName", getConverter().convertString(query.getTagName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(6, paramMap));
        }

        @Test
        public void convertQuery7Param() {
                LdbcQuery7 query = new LdbcQuery7(30786325617380L, 20);

                System.out.println("Nebula LDBC query7 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ7())));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ7())))
                                .build();

                System.out.println();
                System.out.println(paramFilling(7, paramMap));
        }

        @Test
        public void convertQuery8Param() {
                LdbcQuery8 query = new LdbcQuery8(30786325602099L, 20);

                System.out.println("Nebula LDBC query8 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ8())));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ8())))
                                .build();

                System.out.println();
                System.out.println(paramFilling(8, paramMap));
        }

        @Test
        public void convertQuery9Param() {
                LdbcQuery9 query = new LdbcQuery9(21990232978350L, new Date(1337990400000L), 20);

                System.out.println("Nebula LDBC query9 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ9())));
                System.out.println("maxDate: " + getConverter().convertDateTime(query.getMaxDate()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ9())))
                                .put("maxDate", getConverter().convertDateTime(query.getMaxDate()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(9, paramMap));
        }

        @Test
        public void convertQuery10Param() {
                LdbcQuery10 query = new LdbcQuery10(65504, 5, 20);

                System.out.println("Nebula LDBC query10 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ10())));
                System.out.println("month: " + getConverter().convertInteger(query.getMonth()));
                System.out.println("nextMonth: " + getConverter().convertInteger(query.getMonth() % 12 + 1));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ10())))
                                .put("month", getConverter().convertInteger(query.getMonth()))
                                .put("nextMonth", getConverter().convertInteger(query.getMonth() % 12 + 1))
                                .build();

                System.out.println();
                System.out.println(paramFilling(10, paramMap));
        }

        @Test
        public void convertQuery11Param() {
                LdbcQuery11 query = new LdbcQuery11(65504, "Papua_New_Guinea", 2010, 20);

                System.out.println("Nebula LDBC query11 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ11())));
                System.out.println("countryName: " + getConverter().convertString(query.getCountryName()));
                System.out.println("workFromYear: " + getConverter().convertInteger(query.getWorkFromYear()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ11())))
                                .put("countryName", getConverter().convertString(query.getCountryName()))
                                .put("workFromYear", getConverter().convertInteger(query.getWorkFromYear()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(11, paramMap));
        }

        @Test
        public void convertQuery12Param() {
                LdbcQuery12 query = new LdbcQuery12(2199023521857L, "Wrestler", 20);

                System.out.println("Nebula LDBC query12 parameter convert.");
                System.out.println("personId: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPersonIdQ12())));
                System.out.println("tagClassName: " + getConverter().convertString(query.getTagClassName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("personId",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter()
                                                                                .convertId(query.getPersonIdQ12())))
                                .put("tagClassName", getConverter().convertString(query.getTagClassName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(12, paramMap));
        }

        @Test
        public void convertQuery13Param() {
                LdbcQuery13 query = new LdbcQuery13(8796093163356L, 4398046593165L);

                System.out.println("Nebula LDBC query13 parameter convert.");
                System.out.println("person1Id: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPerson1IdQ13StartNode())));
                System.out.println("person2Id: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPerson2IdQ13EndNode())));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("person1Id",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter().convertId(
                                                                                query.getPerson1IdQ13StartNode())))
                                .put("person2Id",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter().convertId(
                                                                                query.getPerson2IdQ13EndNode())))
                                .build();

                System.out.println();
                System.out.println(paramFilling(13, paramMap));
        }

        @Test
        public void convertQuery14Param() {
                LdbcQuery14 query = new LdbcQuery14(8796093163356L, 15393162953088L);

                System.out.println("Nebula LDBC query14 parameter convert.");
                System.out.println("person1Id: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPerson1IdQ14StartNode())));
                System.out.println("person2Id: " + getConverter()
                                .convertString(NebulaID.PERSON_ID_PREFIX
                                                + getConverter().convertId(query.getPerson2IdQ14EndNode())));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put("person1Id",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter().convertId(
                                                                                query.getPerson1IdQ14StartNode())))
                                .put("person2Id",
                                                getConverter().convertString(
                                                                NebulaID.PERSON_ID_PREFIX + getConverter().convertId(
                                                                                query.getPerson2IdQ14EndNode())))
                                .build();

                System.out.println();
                System.out.println(paramFilling(14, paramMap));
        }

}
