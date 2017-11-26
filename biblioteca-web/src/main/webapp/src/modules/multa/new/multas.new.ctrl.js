(function (ng) {
    var mod = ng.module("multaModule");
    mod.constant("multasContext", "api/999/multas");
    mod.controller('multaNewCtrl', ['$scope', '$http', 'multasContext' , '$state', '$rootScope',
        function ($scope, $http, multasContext, $state, $rootScope) {
            $scope.createMulta = function () {
                var feIn=$scope.diaFechaInicio+'/'+$scope.mesFechaInicio+'/'+$scope.anioFechaInicio;
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/multas', {
                    fecha: feIn,
                    costo: $scope.costo,
                    pagada: $scope.pagada,
                    descripcion: $scope.descripcion
                }).then(function (response) {
                    //Multa created successfully
                    $state.go('multaList', {multaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
