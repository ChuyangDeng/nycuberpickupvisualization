var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('palantir', { title: 'Palantir Uber Pickup' });
});

module.exports = router;