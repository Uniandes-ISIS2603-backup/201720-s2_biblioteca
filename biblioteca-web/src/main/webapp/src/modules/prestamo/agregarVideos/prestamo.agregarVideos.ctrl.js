(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "prestamos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('prestamoAgregarVideosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {           
            
            $http.get('api/999/videos/disponibles').then(function (response) {
                    $scope.allVideosDisponibles = response.data;
                });
                
                $scope.agregarVideoPrestamo = function (laId) {
                $http.put('api/999/usuarios/' + $state.params.usuarioId + '/prestamos/' + $state.params.prestamoId + '/videos/actual/prestamo/' + laId, {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
            



            
        }
    ]);
}
)(angular);