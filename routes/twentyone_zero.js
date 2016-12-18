var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('twentyone_zero', { title: '21:00 - 24:00' });
});

module.exports = router;

/**
 * Created by chuyangdeng on 12/17/16.
 */
