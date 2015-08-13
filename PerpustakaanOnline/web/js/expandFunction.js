/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

    //Content boxes expand/collapse
    $(".initial-expand").hide();

    $("div.content-module-heading").click(function() {
        $(this).next("div.content-module-main").slideToggle();

        $(this).children(".expand-collapse-text").toggle();
    });

});
