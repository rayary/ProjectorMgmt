package ProjectorMgmt;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

class ArrayDataResponse {

    private ArrayList<Projector> data;

    public ArrayDataResponse (ArrayList<Projector> data){
        this.data = data;
    }

    public ArrayList<Projector> getData () {
        return this.data;
    }

}

class DataResponse {

    private ObjectNode data;

    public DataResponse (ObjectNode data){
        this.data = data;
    }

    public ObjectNode getData () {
        return this.data;
    }

}


@RestController
public class ProjectorMgmtController {

    @Autowired
    Database db;

    @RequestMapping(value = "/projectors", method = RequestMethod.GET)
    public ResponseEntity<ArrayDataResponse> listProjectors() {
        ArrayList<Projector> equipmentList = db.getEquipmentList();
        equipmentList = null;
        if (equipmentList != null) {
            return new ResponseEntity(new ArrayDataResponse(db.getEquipmentList()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public DataResponse addReservation() {
        return (new DataResponse(db.setReservation("2018-04-28", "12:00", "12:30")));

    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
    public DataResponse retrieveReservation (@PathVariable("id") String id) {
        // todo: validate id is int
        return (new DataResponse(db.getReservation(Integer.valueOf(id))));
    }

    @RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET)
    public DataResponse deleteReservation(@PathVariable("id") String id) {
        // todo: validate id is int
        return (new DataResponse(db.removeReservation(Integer.valueOf(id))));
    }

}
