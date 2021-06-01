/*
	Licensed under (https://colorlib.com/wp/template/signup-form-02/)
	by https://colorlib.com/wp/templates/
	Free for personal and commercial use under the CCA 3.0 license
*/
$(function() {

	$('.btn-link[aria-expanded="true"]').closest('.accordion-item').addClass('active');
	$('.collapse').on('show.bs.collapse', function() {
		$(this).closest('.accordion-item').addClass('active');
	});

	$('.collapse').on('hidden.bs.collapse', function() {
		$(this).closest('.accordion-item').removeClass('active');
	});



});


