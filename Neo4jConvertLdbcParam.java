package neo4j;

import org.ldbcouncil.snb.impls.workloads.converter.Converter;
import org.ldbcouncil.snb.impls.workloads.cypher.converter.CypherConverter;
import org.ldbcouncil.snb.driver.workloads.interactive.*;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import java.util.Date;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Calendar;
import java.util.TimeZone;

public class Neo4jConvertLdbcParam {
        public static String QUERY_PREFIX = Neo4jConvertLdbcParam.class.getResource("/").getFile()
                        + "../../queries/interactive-complex-";
        public static String QUERY_SUFFIX = ".cypher";

        public Map<Integer, String> queries = new HashMap<>();

        public Neo4jConvertLdbcParam() {
                for (int i = 1; i <= 14; i++) {
                        String filename = QUERY_PREFIX + i + QUERY_SUFFIX;
                        // System.out.println(filename);
                        // System.out.println(Neo4jConvertLdbcParam.class.getResource("/").getFile());
                        try {
                                String query = new String(Files.readAllBytes(Paths.get(filename)));
                                queries.put(i, query);
                        } catch (IOException e) {
                                System.err.printf("Unable to load query from file: %s\n", filename);
                        }
                }
                // System.out.println(queries.get(2));
        }

        protected Converter getConverter() {
                return new CypherConverter();
        }

        protected String getParameterPrefix() {
                return "$";
        }

        protected String getParameterPostfix() {
                return "";
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

        static protected Date addDays(Date startDate, int days) {
                final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                cal.setTime(startDate);
                cal.add(Calendar.DATE, days);
                return cal.getTime();
        }

        @Test
        public void convertQuery1Param() {
                LdbcQuery1 query = new LdbcQuery1(65504, "John", 20);
                System.out.println("Neo4j LDBC query1 parameter convert.");
                System.out.println(LdbcQuery1.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ1()));
                System.out.println(LdbcQuery1.FIRST_NAME + ": " + getConverter().convertString(query.getFirstName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery1.PERSON_ID, getConverter().convertId(query.getPersonIdQ1()))
                                .put(LdbcQuery1.FIRST_NAME, getConverter().convertString(query.getFirstName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(1, paramMap));
        }

        @Test
        public void convertQuery2Param() {
                LdbcQuery2 query = new LdbcQuery2(2199023521857L, new Date(1313798400000L), 20);
                System.out.println("Neo4j LDBC query2 parameter convert.");
                System.out.println(LdbcQuery2.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ2()));
                System.out.println(LdbcQuery2.MAX_DATE + ": " + getConverter().convertId(query.getMaxDate().getTime()));
                System.out.println(LdbcQuery2.LIMIT + ": " + query.getLimit());

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery2.PERSON_ID, getConverter().convertId(query.getPersonIdQ2()))
                                .put(LdbcQuery2.MAX_DATE, getConverter().convertId(query.getMaxDate().getTime()))
                                .put(LdbcQuery2.LIMIT, getConverter().convertId(query.getLimit()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(2, paramMap));
        }

        @Test
        public void convertQuery3Param() {
                LdbcQuery3 query = new LdbcQuery3(8796093163356L, "Papua_New_Guinea", "Namibia",
                                new Date(1306886400000L), 52, 20);
                final Date endDate = addDays(query.getStartDate(), query.getDurationDays());

                System.out.println("Neo4j LDBC query3 parameter convert.");
                System.out.println(LdbcQuery3.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ3()));
                System.out.println(LdbcQuery3.COUNTRY_X_NAME + ": "
                                + getConverter().convertString(query.getCountryXName()));
                System.out.println(LdbcQuery3.COUNTRY_Y_NAME + ": "
                                + getConverter().convertString(query.getCountryYName()));
                System.out.println(LdbcQuery3.START_DATE + ": "
                                + getConverter().convertId(query.getStartDate().getTime()));
                System.out.println("endDate" + ": " + getConverter().convertId(endDate.getTime()));
                System.out.println(LdbcQuery3.LIMIT + ": " + getConverter().convertId(query.getLimit()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery3.PERSON_ID, getConverter().convertId(query.getPersonIdQ3()))
                                .put(LdbcQuery3.COUNTRY_X_NAME, getConverter().convertString(query.getCountryXName()))
                                .put(LdbcQuery3.COUNTRY_Y_NAME, getConverter().convertString(query.getCountryYName()))
                                .put(LdbcQuery3.START_DATE, getConverter().convertId(query.getStartDate().getTime()))
                                .put("endDate", getConverter().convertId(endDate.getTime()))
                                .put(LdbcQuery3.LIMIT, getConverter().convertId(query.getLimit()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(3, paramMap));
        }

        @Test
        public void convertQuery4Param() {
                LdbcQuery4 query = new LdbcQuery4(2199023472955L, new Date(1317427200000L), 52,
                                20);
                final Date endDate = addDays(query.getStartDate(), query.getDurationDays());

                System.out.println("Neo4j LDBC query4 parameter convert.");
                System.out.println(LdbcQuery4.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ4()));
                System.out.println(LdbcQuery4.START_DATE + ": "
                                + getConverter().convertId(query.getStartDate().getTime()));
                System.out.println("endDate" + ": " + getConverter().convertId(endDate.getTime()));
                System.out.println(LdbcQuery4.LIMIT + ": " + getConverter().convertId(query.getLimit()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery4.PERSON_ID, getConverter().convertId(query.getPersonIdQ4()))
                                .put(LdbcQuery4.START_DATE, getConverter().convertId(query.getStartDate().getTime()))
                                .put("endDate", getConverter().convertId(endDate.getTime()))
                                .put(LdbcQuery4.LIMIT, getConverter().convertId(query.getLimit()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(4, paramMap));
        }

        @Test
        public void convertQuery5Param() {
                LdbcQuery5 query = new LdbcQuery5(8796093163356L, new Date(1348272000000L), 20);

                System.out.println("Neo4j LDBC query5 parameter convert.");
                System.out.println(LdbcQuery5.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ5()));
                System.out.println(LdbcQuery5.MIN_DATE + ": " + getConverter().convertId(query.getMinDate().getTime()));
                System.out.println(LdbcQuery5.LIMIT + ": " + getConverter().convertId(query.getLimit()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery5.PERSON_ID, getConverter().convertId(query.getPersonIdQ5()))
                                .put(LdbcQuery5.MIN_DATE, getConverter().convertId(query.getMinDate().getTime()))
                                .put(LdbcQuery5.LIMIT, getConverter().convertId(query.getLimit()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(5, paramMap));
        }

        @Test
        public void convertQuery6Param() {
                LdbcQuery6 query = new LdbcQuery6(65504L, "Gordon_Brown", 20);

                System.out.println("Neo4j LDBC query6 parameter convert.");
                System.out.println(LdbcQuery6.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ6()));
                System.out.println(LdbcQuery6.TAG_NAME + ": " + getConverter().convertString(query.getTagName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery6.PERSON_ID, getConverter().convertId(query.getPersonIdQ6()))
                                .put(LdbcQuery6.TAG_NAME, getConverter().convertString(query.getTagName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(6, paramMap));
        }

        @Test
        public void convertQuery7Param() {
                LdbcQuery7 query = new LdbcQuery7(30786325617380L, 20);

                System.out.println("Neo4j LDBC query7 parameter convert.");
                System.out.println(LdbcQuery7.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ7()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery7.PERSON_ID, getConverter().convertId(query.getPersonIdQ7()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(7, paramMap));
        }

        @Test
        public void convertQuery8Param() {
                LdbcQuery8 query = new LdbcQuery8(30786325602099L, 20);

                System.out.println("Neo4j LDBC query8 parameter convert.");
                System.out.println(LdbcQuery8.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ8()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery8.PERSON_ID, getConverter().convertId(query.getPersonIdQ8()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(8, paramMap));
        }

        @Test
        public void convertQuery9Param() {
                LdbcQuery9 query = new LdbcQuery9(21990232978350L, new Date(1337990400000L), 20);

                System.out.println("Neo4j LDBC query9 parameter convert.");
                System.out.println(LdbcQuery9.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ9()));
                System.out.println(LdbcQuery9.MAX_DATE + ": " + getConverter().convertId(query.getMaxDate().getTime()));
                System.out.println(LdbcQuery9.LIMIT + ": " + getConverter().convertId(query.getLimit()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery9.PERSON_ID, getConverter().convertId(query.getPersonIdQ9()))
                                .put(LdbcQuery9.MAX_DATE, getConverter().convertId(query.getMaxDate().getTime()))
                                .put(LdbcQuery9.LIMIT, getConverter().convertId(query.getLimit()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(9, paramMap));
        }

        @Test
        public void convertQuery10Param() {
                LdbcQuery10 query = new LdbcQuery10(65504, 5, 20);

                System.out.println("Neo4j LDBC query10 parameter convert.");
                System.out.println(LdbcQuery10.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ10()));
                System.out.println(LdbcQuery10.MONTH + ": " + getConverter().convertId(query.getMonth()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery10.PERSON_ID, getConverter().convertId(query.getPersonIdQ10()))
                                .put(LdbcQuery10.MONTH, getConverter().convertId(query.getMonth()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(10, paramMap));
        }

        @Test
        public void convertQuery11Param() {
                LdbcQuery11 query = new LdbcQuery11(65504, "Papua_New_Guinea", 2010, 20);

                System.out.println("Neo4j LDBC query11 parameter convert.");
                System.out.println(LdbcQuery11.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ11()));
                System.out.println(
                                LdbcQuery11.COUNTRY_NAME + ": " + getConverter().convertString(query.getCountryName()));
                System.out.println(
                                LdbcQuery11.WORK_FROM_YEAR + ": " + getConverter().convertId(query.getWorkFromYear()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery11.PERSON_ID, getConverter().convertId(query.getPersonIdQ11()))
                                .put(LdbcQuery11.COUNTRY_NAME, getConverter().convertString(query.getCountryName()))
                                .put(LdbcQuery11.WORK_FROM_YEAR, getConverter().convertId(query.getWorkFromYear()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(11, paramMap));
        }

        @Test
        public void convertQuery12Param() {
                LdbcQuery12 query = new LdbcQuery12(2199023521857L, "Wrestler", 20);

                System.out.println("Neo4j LDBC query12 parameter convert.");
                System.out.println(LdbcQuery12.PERSON_ID + ": " + getConverter().convertId(query.getPersonIdQ12()));
                System.out.println(LdbcQuery12.TAG_CLASS_NAME + ": "
                                + getConverter().convertString(query.getTagClassName()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery12.PERSON_ID, getConverter().convertId(query.getPersonIdQ12()))
                                .put(LdbcQuery12.TAG_CLASS_NAME, getConverter().convertString(query.getTagClassName()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(12, paramMap));
        }

        @Test
        public void convertQuery13Param() {
                LdbcQuery13 query = new LdbcQuery13(8796093163356L, 4398046593165L);

                System.out.println("Neo4j LDBC query13 parameter convert.");
                System.out.println(LdbcQuery13.PERSON1_ID + ": "
                                + getConverter().convertId(query.getPerson1IdQ13StartNode()));
                System.out.println(LdbcQuery13.PERSON2_ID + ": "
                                + getConverter().convertId(query.getPerson2IdQ13EndNode()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery13.PERSON1_ID, getConverter().convertId(query.getPerson1IdQ13StartNode()))
                                .put(LdbcQuery13.PERSON2_ID, getConverter().convertId(query.getPerson2IdQ13EndNode()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(13, paramMap));
        }

        @Test
        public void convertQuery14Param() {
                LdbcQuery14 query = new LdbcQuery14(8796093163356L, 15393162953088L);

                System.out.println("Neo4j LDBC query14 parameter convert.");
                System.out.println(LdbcQuery14.PERSON1_ID + ": "
                                + getConverter().convertId(query.getPerson1IdQ14StartNode()));
                System.out.println(LdbcQuery14.PERSON2_ID + ": "
                                + getConverter().convertId(query.getPerson2IdQ14EndNode()));

                Map<String, Object> paramMap = new ImmutableMap.Builder<String, Object>()
                                .put(LdbcQuery14.PERSON1_ID, getConverter().convertId(query.getPerson1IdQ14StartNode()))
                                .put(LdbcQuery14.PERSON2_ID, getConverter().convertId(query.getPerson2IdQ14EndNode()))
                                .build();

                System.out.println();
                System.out.println(paramFilling(14, paramMap));
        }

}
