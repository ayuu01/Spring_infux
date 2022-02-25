package com.example.Spring_infux.service;


import com.example.Spring_infux.entity.Temperature;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InfluxService {

    private static char[]  token="7y86pph6oeowqb7ejnx4".toCharArray();
    private static String org="primary";
    private static String bucket="primary";

    private InfluxDBClient connect(){
        InfluxDBClient client= InfluxDBClientFactory
                .create("http://localhost:8086",token,org,bucket);
        return client;
    }

    public void writeData(){
        connect();
        InfluxDBClient client=InfluxDBClientFactory
                .create("http://localhost:8086",token,org,bucket);

        //  Write data

        WriteApiBlocking writeApi= client.getWriteApiBlocking();

        // Write by Data Point

        Point point= Point.measurement("temperature")
                .addTag("location","west")
                .addField("value",550)
                .time(Instant.now(), WritePrecision.MS);
        writeApi.writePoint(point);


        // Write by LineProtocol
        writeApi.writeRecord(WritePrecision.NS, "temperature,location=north value=60.0");


        // Write by POJO
        Temperature temperature=new Temperature();
        temperature.location="south";
        temperature.setValue(String.valueOf(620));
        temperature.setTime(Instant.now());

        writeApi.writeMeasurement(WritePrecision.NS,temperature);
        client.close();

    }

    public void readData(){
        //connect();
        InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

        //Query data
        String query = "from(bucket:\"primary\") |> range(start: 0)";
        QueryApi queryApi = client.getQueryApi();

        //Map to POJO
        List<Temperature> temperatures = queryApi.query(query, Temperature.class);
        for(Temperature temperature : temperatures){
            System.out.println("location:" +temperature.getLocation()+ ", " + "value:"+ temperature.getValue());
        }
        client.close();
    }
}
