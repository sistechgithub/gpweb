'use strict';

describe('Controller Tests', function() {

    describe('Sub_grupo Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockSub_grupo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockSub_grupo = jasmine.createSpy('MockSub_grupo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Sub_grupo': MockSub_grupo
            };
            createController = function() {
                $injector.get('$controller')("Sub_grupoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'gpwebApp:sub_grupoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
