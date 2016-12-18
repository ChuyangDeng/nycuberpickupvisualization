var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('eighteen', { title: '18:00 - 21:00' });
});

module.exports = router;