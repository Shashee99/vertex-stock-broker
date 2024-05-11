package com.shashee.broker.assets;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetsRestApi {
  private static final Logger LOG = LoggerFactory.getLogger(AssetsRestApi.class);


  public static void attach(Router parent){
    parent.get("/assets").handler( ctx ->
      {
        String destination = System.getProperty("user.home");
        System.out.println(destination);
        final JsonArray response = new JsonArray();
        response
          .add(new Asset("AAPL"))
          .add(new Asset("AMZN"))
          .add(new Asset("NFLX"))
          .add(new Asset("TSLA"));
        LOG.info("Path {} responds with {}",ctx.normalizedPath(),response.encode());
        ctx.response().end(response.toBuffer());
      }
    );

  }
//  public static void fileUpload(Router parent){
//    parent.post("/upload").handler( ctx -> {
//      FileUpload fileUpload = ctx.fileUploads().iterator().next();
//      String uploadedFileName = fileUpload.uploadedFileName();
//
//      // Define the location to save the file
//      String destination = "/path/to/your/upload/folder/" + fileUpload.fileName();
//
//      // Move the uploaded file to the desired location
//      vertx.fileSystem().move(uploadedFileName, destination, result -> {
//        if (result.succeeded()) {
//          // File uploaded successfully
//          HttpServerResponse response = context.response();
//          response.setStatusCode(200).end("File uploaded successfully");
//        } else {
//          // Failed to upload file
//          result.cause().printStackTrace();
//          context.fail(500);
//        }
//      });
//    });
//  }
}
