(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('FilialSearch', FilialSearch);

    FilialSearch.$inject = ['$resource'];

    function FilialSearch($resource) {
        var resourceUrl =  'api/_search/filials/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
