var express = require('express');
var router = express.Router();
var mongodb = require('mongodb');

var MongoClient = mongodb.MongoClient;
var uri = "mongodb://chuyang:xiaogougou4250@ds127928.mlab.com:27928/popularity";

function mongoQuery(res) {
    MongoClient.connect(uri, function(err, db) {
        if (err) {
            res.send(err);
            console.log("Failed to connect to MongoDB...");
        } else {
            console.log("Connected to MongoDB");

            var collection = db.collection('tweets');

            collection.find({}).toArray(function(err, result) {
                if(err) {
                    res.send(err);
                } else if (result.length) {
                    for (var i = 0; i < result.length; i++) {
                        console.log("user: " + result[i]["USER"] + "\n" +
                                    "tweets: " + result[i]["TWEETS"]);
                    }

                    res.render('tweets', {
                        result: result
                    });

                    db.close();
                } else {
                    res.send("No results.")
                }
            });
        }
    });
}

router.get('/', function(req, res) {
    mongoQuery(res);
});

module.exports = router;