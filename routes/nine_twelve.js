var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('nine_twelve', { title: '9:00 - 12:00' });
});

module.exports = router;