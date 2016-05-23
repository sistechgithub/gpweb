(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('Sub_grupoSearch', Sub_grupoSearch);

    Sub_grupoSearch.$inject = ['$resource'];

    function Sub_grupoSearch($resource) {
        var resourceUrl =  'api/_search/sub-grupos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
