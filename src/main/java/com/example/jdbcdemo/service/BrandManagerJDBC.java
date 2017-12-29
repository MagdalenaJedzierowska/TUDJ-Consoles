package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandManagerJDBC implements BrandManager {

    private Connection connection;

    private PreparedStatement addBrandStmt;		    //CREATE
    private PreparedStatement getAllBrandsStmt;		//READ
    private PreparedStatement updateBrandByNameStmt;//UPDATE
    private PreparedStatement deleteAllBrandsStmt;	//DELETE
    private PreparedStatement deleteBrandByNameStmt;//DELETE
    private PreparedStatement searchBrandByNameStmt;//FIND

    ConnectionManagerJDBC conn = new ConnectionManagerJDBC();

    public BrandManagerJDBC() {
        try {
            connection = conn.getConnection();

            addBrandStmt = connection
                    .prepareStatement("INSERT INTO Brand (brand_name, country) VALUES (?, ?)");
            getAllBrandsStmt = connection
                    .prepareStatement("SELECT id_brand, brand_name, country FROM Brand");
            updateBrandByNameStmt = connection
                    .prepareStatement("UPDATE Brand SET brand_name = ?, country = ? WHERE brand_name = ?");
            deleteAllBrandsStmt = connection
                    .prepareStatement("DELETE FROM Brand");
            deleteBrandByNameStmt = connection
                    .prepareStatement("DELETE FROM Brand WHERE brand_name = ?");
            searchBrandByNameStmt = connection
                    .prepareStatement("SELECT id_brand, brand_name, country FROM Brand WHERE brand_name = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addBrand(Brand brand) {
        int count = 0;

        try {
            addBrandStmt.setString(1, brand.getBrandName());
            addBrandStmt.setString(2, brand.getCountry());

            count = addBrandStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void addBrands(List<Brand> brands){

        try {
            connection.setAutoCommit(false);
            for (Brand brand : brands) {
                addBrandStmt.setString(1, brand.getBrandName());
                addBrandStmt.setString(2, brand.getCountry());
                addBrandStmt.executeUpdate();
            }
            connection.commit();

        } catch (SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                //!!! ALERT !!!
            }
        }
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();

        try {
            ResultSet resultSet = getAllBrandsStmt.executeQuery();
            brands = getFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public int updateBrand(Brand brand, Brand newBrand){
        int count = 0;

        try{
            updateBrandByNameStmt.setString(1, newBrand.getBrandName());
            updateBrandByNameStmt.setString(2, newBrand.getCountry());
            updateBrandByNameStmt.setString(3, brand.getBrandName());
            count = updateBrandByNameStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int deleteBrands(List<Brand> brands){
        int count = 0;

        try{

            connection.setAutoCommit(false);

            for(Brand brand : brands){
                deleteBrandByNameStmt.setString(1, brand.getBrandName());
                count += deleteBrandByNameStmt.executeUpdate();

            }
            connection.commit();

        }catch (SQLException exception){

            try{
                connection.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return count;
    }

    void clearBrands() {
        try {
            deleteAllBrandsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Brand> searchBrandByName(Brand brand){
        List<Brand> brands = new ArrayList<>();

        try{
            searchBrandByNameStmt.setString(1, brand.getBrandName());
            brands = getFromResultSet(searchBrandByNameStmt.executeQuery());

        }catch (SQLException e){
            e.printStackTrace();
        }
        return brands;
    }

    private List<Brand> getFromResultSet (ResultSet resultSet){
        List<Brand> brands = new ArrayList<>();

        try {

            while (resultSet.next()) {
                Brand b = new Brand();
                b.setId(resultSet.getInt("id_brand"));
                b.setBrandName(resultSet.getString("brand_name"));
                b.setCountry(resultSet.getString("country"));
                brands.add(b);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return brands;
    }
}
