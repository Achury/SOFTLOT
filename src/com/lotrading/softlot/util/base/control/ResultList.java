package com.lotrading.softlot.util.base.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultList implements Serializable{
	ArrayList _objetos = new ArrayList();

    public ResultList() {}

    public ResultList(List listaObjetos) {
        if (listaObjetos != null) {
            _objetos = (ArrayList) listaObjetos;
        }
    }

    public void setObjetos(List nuevaListaObjetos) {
        if (nuevaListaObjetos == null) {
            return;
        }

        if (this._objetos != null) {
            this._objetos.clear();
            this._objetos = null;
        }

        this._objetos = new ArrayList();
        this._objetos.addAll(nuevaListaObjetos);
    }

    public void agregarObjetos(List objetos) {
        if (objetos != null) {
            _objetos.addAll(objetos);
        } else {
            setObjetos(objetos);
        }
    }

    public List getObjetos() {
        return _objetos;
    }

    public Object get(int posicion) {
        Object _tmpRetorno = null;

        if ((_objetos != null) || (_objetos.size() > 0)) {
            if (posicion <= _objetos.size()) {
                _tmpRetorno = _objetos.get(posicion);
            }
        }

        return _tmpRetorno;
    }

    public boolean actualizarObjeto(Object objetoActualizado) {
        boolean _tmpRetorno = false;

        if (_objetos == null) {
            _tmpRetorno = false;
        }

        int _tmpPos = getPosicionObjeto(objetoActualizado);

        if (_tmpPos >= 0) {
            _objetos.set(_tmpPos, objetoActualizado);
            _tmpRetorno = true;
        }

        return _tmpRetorno;
    }

    public void agregarObjeto(Object objeto) {
        if (_objetos == null) {
            _objetos = new ArrayList();
        }

        _objetos.add(objeto);
    }

    public void borrarObjeto(Object objReferencia) {
        if (_objetos == null) {
            return;
        }

        int _tmpPos = getPosicionObjeto(objReferencia);

        if (_tmpPos >= 0) {
            _objetos.remove(_tmpPos);
        }
    }

    public void borrarObjeto(int posicion) {
        if ((_objetos != null) || (_objetos.size() > 0)) {
            if (posicion <= _objetos.size()) {
                _objetos.remove(posicion);
            }
        }
    }

    public boolean existeObjeto(Object objReferencia) {
        boolean _tmpRet = false;

        if (_objetos == null) {
            return false;
        }

        if (getPosicionObjeto(objReferencia) >= 0) {
            _tmpRet = true;
        }

        return _tmpRet;
    }

    public int getPosicionObjeto(Object objReferencia) {
        int _tmpRet = -1;

        if (_objetos == null) {
            return -1;
        }

        for (int i = 0; i < _objetos.size(); i++) {
            Object _tmpObject = _objetos.get(i);

            if (_tmpObject.equals(objReferencia)) {
                _tmpRet = i;
            }
        }

        return _tmpRet;
    }

    public int getCantidadObjetos() {
        if (_objetos != null) {
            return _objetos.size();
        } else {
            return 0;
        }
    }

    public void ordenar(Comparator comparador) {
        Collections.sort(_objetos, comparador);
    }
}
