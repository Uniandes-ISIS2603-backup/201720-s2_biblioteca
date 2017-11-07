(
        function (ng) {
            var mod = ng.module("multaModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("multaContext", "api/books");
            mod.controller('multaUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'multaContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state, multaContext, $rootScope, $filter) {
                    $rootScope.edit = true;       
                    
                     var idMulta = $state.params.multaId;
                    var idUsuario = $state.params.usuarioId;
                    console.info(usuarioContext+'/' + idUsuario);
                    $http.get('api/999/usuarios/' + $state.params.usuarioId+'/multas/'+idMulta).then(function (response) {
                        var multa = response.data;
                        $scope.fecha = multa.fecha;
                        $scope.costo = multa.costo;
                        $scope.pagada = multa.pagada;
                        $scope.descripcion = multa.descripcion;
                    });     
                                        

                    $scope.updateMulta = function () {                   
                        $http.put('api/999/usuarios/' + $state.params.usuarioId+'/multas/'+idMulta, {
                            fecha: $scope.fecha,
                            costo: $scope.costo,
                            pagada: $scope.pagada,
                            descripcion: $scope.descripcion
                        }).then(function (response) {                            
                            $state.go('multaList', {multaId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);