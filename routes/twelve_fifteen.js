var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('twelve_fifteen', { title: '12:00 - 15:00' });
});

module.exports = router;