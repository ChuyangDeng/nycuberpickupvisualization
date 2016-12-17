var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('fifteen', { title: '15:00 - 18:00' });
});

module.exports = router;