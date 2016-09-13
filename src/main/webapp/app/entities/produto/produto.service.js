(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Produto', Produto);

    Produto.$inject = ['$resource', 'DateUtils'];

    function Produto ($resource, DateUtils) {
        var resourceUrl =  'api/produtos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                	
                	//Convert Date from Grupo
                	if(data.grupo){                		
                		data.grupo.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.grupo.dtOperacao.year, data.grupo.dtOperacao.month, data.grupo.dtOperacao.day)
                		);
                		
                	};
                	
                	//Convert Date from SubGrupo
                	if(data.subgrupo){                		
                		data.subgrupo.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.subgrupo.dtOperacao.year, data.subgrupo.dtOperacao.month, data.subgrupo.dtOperacao.day)
                		);
                		
                	};
                	
                	//Convert Date from Marca
                	if(data.marca){                		
                		data.marca.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.marca.dtOperacao.year, data.marca.dtOperacao.month, data.marca.dtOperacao.day)
                		);
                	};
                	
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                	
                	//Convert Date from Grupo
                	if(data.grupo){                		
                		data.grupo.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.grupo.dtOperacao.year, data.grupo.dtOperacao.month, data.grupo.dtOperacao.day)
                		);                		
                	};
                	
                	//Convert Date from SubGrupo
                	if(data.subgrupo){                		
                		data.subgrupo.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.subgrupo.dtOperacao.year, data.subgrupo.dtOperacao.month, data.subgrupo.dtOperacao.day)
                		);                		
                	};
                	
                	//Convert Date from Marca
                	if(data.marca){                		
                		data.marca.dtOperacao = DateUtils.convertLocalDateToServer(
                				//This convertion is necessary cause the component scselect returns an object date
                				new Date(data.marca.dtOperacao.year, data.subgrupo.dtOperacao.month, data.marca.dtOperacao.day)
                		);                		
                	};
                	
                    return angular.toJson(data);
                }
            }
        });
    }
})();
