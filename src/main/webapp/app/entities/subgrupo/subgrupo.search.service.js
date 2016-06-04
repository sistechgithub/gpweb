(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('SubgrupoSearch', SubgrupoSearch);

    SubgrupoSearch.$inject = ['$resource'];

    function SubgrupoSearch($resource) {
        var resourceUrl =  'api/_search/subgrupos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
