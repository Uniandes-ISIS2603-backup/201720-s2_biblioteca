(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "prestamos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('prestamoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            if ($state.params.usuarioId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/prestamos').then(function (response) {
                    $scope.prestamoRecords = response.data;                    
                });
            }
            
            if ($state.params.prestamoId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/prestamos/'+$state.params.prestamoId).then(function (response) {
                    $scope.prestamoRecords = response.data.prestamos;
                    $scope.currentPrestamo = response.data;
                    $scope.currentPrestamoLibros=response.data.libros;
                    $scope.currentPrestamoVideos=response.data.videos;
                    $scope.currentPrestamoSalas=response.data.salas;
                });
            }       
            
            $scope.devolverLibroPrestamo = function (laId) {
                $http.put('api/999/libros/'+laId+'/devolver', {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
            $scope.devolverVideoPrestamo = function (laId) {
                $http.put('api/999/videos/'+laId+'/devolver', {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
            $scope.devolverSalaPrestamo = function (laId) {
                $http.put('api/999/bibliotecas/1/salas/'+laId+'/devolver', {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
        }
    ]);
}
)(angular);