var express = require('express');
var router = express.Router();



/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('week', { title: 'Uber Pickup in a Week'});
});

module.exports = router;