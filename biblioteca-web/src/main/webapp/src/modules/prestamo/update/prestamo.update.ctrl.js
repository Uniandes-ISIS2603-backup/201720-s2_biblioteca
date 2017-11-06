(
        function (ng) {
            var mod = ng.module("prestamoModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("prestamoContext", "api/books");
            mod.controller('prestamoUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;       
                    
                     var idPrestamo = $state.params.prestamoId;
                    var idUsuario = $state.params.usuarioId;
                    console.info(usuarioContext+'/' + idUsuario);
                    $http.get('api/999/usuarios/' + $state.params.usuarioId+'/prestamos/'+idPrestamo).then(function (response) {
                        var prest = response.data;
                        $scope.prestfechaInicio = prest.fechaInicio;
                        $scope.prestfechaFinal = prest.fechaFinal;
                        $scope.prestgeneroMulta = prest.generoMulta;
                        $scope.prestretornado = prest.retornado;
                        $scope.prestexterno = prest.externo;
                    });     
                                        

                    $scope.updatePrestamo = function () {                   
                        $http.put('api/999/usuarios/' + $state.params.usuarioId+'/prestamos/'+idPrestamo, {
                            fechaInicio: $scope.prestfechaInicio,
                            fechaFinal: $scope.prestfechaFinal,
                            generoMulta: $scope.prestgeneroMulta,
                            retornado: $scope.prestretornado,
                            externo: $scope.prestexterno
                        }).then(function (response) {                            
                            $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);