
$(function() {

    $("#contactForm input,#contactForm textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
    
        },
        submitSuccess: function($form, event) {
            event.preventDefault(); 
          
            var name = $("input#name").val();
            var phone = $("input#phone").val();
            var email = $("input#email").val();
            var message = $("textarea#message").val();
            var firstName = name; 

           

        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});



