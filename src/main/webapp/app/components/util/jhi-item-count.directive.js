(function() {
    'use strict';

    var jhiItemCount = {
        template: '<div class="info">' +
                    '<span translate="pagination.showing"> Showing </span> {{(($ctrl.page-1) * 20)==0 ? 1:(($ctrl.page-1) * 20)}} - ' +
                    '{{($ctrl.page * 20) < $ctrl.queryCount ? ($ctrl.page * 20) : $ctrl.queryCount}} ' +
                    '<span translate="pagination.of"> of </span> {{$ctrl.queryCount}} <span translate="pagination.items"> items </span>.' +
                '</div>',
        bindings: {
            page: '<',
            queryCount: '<total'
        }
    };

    angular
        .module('gpwebApp')
        .component('jhiItemCount', jhiItemCount);
})();
