(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "prestamos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('prestamoAgregarSalasCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {           
            
            $http.get('api/999/bibliotecas/1/salas/disponibles').then(function (response) {
                    $scope.allSalasDisponibles = response.data;
                });
                
                var idPrestamo=$state.params.prestamoId;
                $scope.agregarSalaPrestamo = function (laId) {
                    api/999/bibliotecas/1/salas/actual/1/prestamo/3
                $http.put('api/999/bibliotecas/1/salas/actual/' +idPrestamo+ '/prestamo/' + laId, {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
            



            
        }
    ]);
}
)(angular);