var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('six_nine', { title: '06:00 - 09:00' });
});

module.exports = router;