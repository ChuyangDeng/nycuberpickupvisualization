var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('three_six', { title: '03:00 - 06:00' });
});

module.exports = router;