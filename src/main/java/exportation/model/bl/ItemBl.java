package exportation.model.bl;

import exportation.controller.exception.NoItemFoundException;
import lombok.Getter;
import exportation.model.da.ItemDa;
import exportation.model.entity.Item;
import exportation.model.tools.CRUD;

import java.util.List;

public class ItemBl implements CRUD<Item> {
    @Getter
    private static ItemBl itemBl = new ItemBl();

    private ItemBl() {
    }

    //save
    @Override
    public Item save(Item item) throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            itemDa.save(item);
            return item;
        }
    }

    //edit
    @Override
    public Item edit(Item item) throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            if (itemDa.findById(item.getId()) != null) {
                itemDa.edit(item);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    //remove
    @Override
    public Item remove(int id) throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            Item item = itemDa.findById(id);
            if (item != null) {
                itemDa.remove(id);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Item> findAll() throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            List<Item> perosnList = itemDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    //findById
    @Override
    public Item findById(int id) throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            Item item = itemDa.findById(id);
            if (item != null) {
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }


    //findByModel
    public List<Item> findByModel (String model) throws Exception {
        try (ItemDa itemDa = new ItemDa()) {
            List<Item> perosnList = itemDa.findByModel(model);
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

}
