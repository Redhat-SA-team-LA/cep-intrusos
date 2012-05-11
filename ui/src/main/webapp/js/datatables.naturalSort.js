jQuery.fn.dataTableExt.oSort['natural-asc']  = function(a,b) {
    return naturalSort(a,b);
};
 
jQuery.fn.dataTableExt.oSort['natural-desc'] = function(a,b) {
    return naturalSort(a,b) * -1;
};
