(function (ng) {
    var mod = ng.module("salasModule");
    mod.constant("salasContext", "api/999/salas");
    mod.controller('salaNewCtrl', ['$scope', '$http', 'salasContext', '$state', '$rootScope',
        function ($scope, $http, salasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createsala = function () {
                $http.post(salasContext, {
                    capacidad: $scope.salaCapacidad
                }).then(function (response) {
                    //Sala creada exitosamente
                    $state.go('salasList', {salaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
