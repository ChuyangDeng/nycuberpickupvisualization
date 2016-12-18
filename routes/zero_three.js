var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('zero_three', { title: '00:00 - 03:00' });
});

module.exports = router;