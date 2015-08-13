function setSelectedValue(objDropdown, value) {
    console.log(value);
    for (var i = 0; i < objDropdown.options.length; i++) {
        var text = objDropdown.options[i].text.toUpperCase();
        if (text === value.toUpperCase()) {
            objDropdown.options[i].selected = true;
            return;
        }
    }
}
;

function setSelectedbyIdValue(objDropdown, value) {
    console.log('===' + value);
    for (var i = 0; i < objDropdown.options.length; i++) {
        var text = objDropdown.options[i].value.toUpperCase();
        console.log(text);
        if (text === value.toUpperCase()) {
            objDropdown.options[i].selected = true;
            return;
        }
    }
}
;

function setSelectedIndex(objDropdown, index) {
    objDropdown.selectedIndex = index;
}
;

function parseCustomDate(newdate) {

    var year = (newdate.substring(7, 11)).replace(/\s+/g, '');

    var month = (newdate.substring(0, 3)).replace(/\s+/g, '');
    var numMonth = getNumberOfMonth(month);

    var day = (newdate.substring(4, 6)).replace(/\s+/g, '');

    var hour = (newdate.substring(12, 14)).replace(/\s+/g, '');
    var min = (newdate.substring(15, 17)).replace(/\s+/g, '');
    var sec = (newdate.substring(18, 20)).replace(/\s+/g, '');

    //format yyyy-MM-dd hh:mm:ss    
    return year + '-' + numMonth + '-' + day + ' ' + hour + ':' + min + ':' + sec;
}

function getNumberOfMonth(month) {
    if (month === 'Jan') {
        return '01';
    } else if (month === 'Feb') {
        return '02';
    } else if (month === 'Mar') {
        return '03';
    } else if (month === 'Apr') {
        return '04';
    } else if (month === 'May') {
        return '05';
    } else if (month === 'Jun') {
        return '06';
    } else if (month === 'Jul') {
        return '07';
    } else if (month === 'Aug') {
        return '08';
    } else if (month === 'Sep') {
        return '09';
    } else if (month === 'Oct') {
        return '10';
    } else if (month === 'Nov') {
        return '11';
    } else if (month === 'Dec') {
        return '12';
    }
}