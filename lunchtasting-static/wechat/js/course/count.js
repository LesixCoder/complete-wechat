jQuery(function () {
	var options = {
	  useEasing : true, 
	  useGrouping : true, 
	  separator : ',', 
	  decimal : '.', 
	  prefix : '', 
	  suffix : '' 
	};
	var count1 = new CountUp("trainTime-count", 0, 18, 0, 1.5, options);
	var count2 = new CountUp("burnCal-count", 0, 2430, 0, 1.5, options);
	var count3 = new CountUp("fat-count", 0, 1244, 0, 1.5, options);
	$(window).ready(function(){
		count1.start();
		count2.start();
		count3.start();
	})
});