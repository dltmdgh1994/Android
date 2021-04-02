package org.lsh.pokemonbook;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

public class ImageGetter implements Html.ImageGetter {
    Context context;

    public ImageGetter(Context context){
        this.context = context;
    }
    @Override
    public Drawable getDrawable(String source) {
        if ( source.equals( "fire" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.fire);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "bug" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.bug);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "dragon" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.dragon);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "electric" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.electric);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "esper" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.esper);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "fairy" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.fairy);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "fighting" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.fighting);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "flying" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.flying);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "ghost" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.ghost);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "ground" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.ground);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "ice" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.ice);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "iron" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.iron);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "normal" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.normal);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "plant" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.plant);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "poison" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.poison);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "dark" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.dark);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "rock" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.rock);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }else if ( source.equals( "water" ) ){
            Drawable drawable = context.getResources().getDrawable( R.drawable.water);
            drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

            return drawable;
        }
        return null;
    }
}